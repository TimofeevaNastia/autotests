pipeline {
    agent any
    environment {
        LC_ALL = 'en_US.UTF-8'
        LANG = 'en_US.UTF-8'
        LANGUAGE = 'en_US.UTF-8'
    }
    tools {
        gradle 'gradle 7.6.1'
    }


    parameters {
        string(name: 'GIT_URL', defaultValue: 'https://github.com/TimofeevaNastia/autotests.git', description: 'The target git url')
        string(name: 'GIT_BRANCH', defaultValue: 'master', description: 'The target git branch')
        string(name: 'INCLUDE_TAGS', defaultValue: 'addresservice', description: 'The include tags for launch tests')
        choice(name: 'ENV', choices: ['dev', 'test'], description: 'Environment')
    }

    stages {
        stage('Pull from GitHub') {
            steps {
                git([
                        url   : "${params.GIT_URL}",
                        branch: "${params.GIT_BRANCH}"
                ])
            }
        }
        stage('Run gradle clean test') {
            steps {
                bat 'gradle clean test -Denv=dev -DincludeTags=addresservice'
            }
        }
        stage('Backup and Reports') {
            steps {
                archiveArtifacts artifacts: '**/build/', fingerprint: true
            }
            post {
                always {
                    script {
                        // Узнаем ветку репозитория
                        def branch = bat(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD\n').trim().tokenize().last()
                        println("branch= " + branch)

                        // Текст оповещения
                        def message = "${currentBuild.currentResult}: Job ${env.JOB_NAME}, build ${env.BUILD_NUMBER}, branch ${branch}\nTest Summary - ${summary.totalCount}, Failures: ${summary.failCount}, Skipped: ${summary.skipCount}, Passed: ${summary.passCount}\nMore info at: ${env.BUILD_URL}"

                        if (currentBuild.currentResult == 'SUCCESS') {
                            step([$class: 'Mailer', body: message, notifyEveryUnstableBuild: true, recipients: "anasok1997@gmail.com", sendToIndividuals: true])
                            slackSend(message: "Notification from Jenkins Pipeline: " + message)
                        } else {
                            step([$class: 'Mailer', body: message, notifyEveryUnstableBuild: true, recipients: "anasok1997@gmail.com", sendToIndividuals: true])
                            slackSend(message: "Notification from Jenkins Pipeline: " + message)
                        }

                        // Формирование отчета
                        allure([
                                includeProperties: false,
                                jdk              : '',
                                properties       : [],
                                reportBuildPolicy: 'ALWAYS',
                                results          : [[path: 'api-tests/build/allure-results']]
                        ])
                        println('allure report created')


                        // Текст оповещения
                        //def message = "${currentBuild.currentResult}: Job ${env.JOB_NAME}, build ${env.BUILD_NUMBER}, branch ${branch}\nTest Summary - ${summary.totalCount}, Failures: ${summary.failCount}, Skipped: ${summary.skipCount}, Passed: ${summary.passCount}\nMore info at: ${env.BUILD_URL}"
                        println("message= " + message)
                    }
                }
            }
        }
    }
}