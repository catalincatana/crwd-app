#!groovy

pipeline {
    agent any
    stages {
        stage('Start Build') {
            steps {
                build job: 'BuildCrwdApp'
            }
        }
        stage('Deploy') {
            steps {
                build job: 'DeployCrwdApp'
            }
        }
    }
}
