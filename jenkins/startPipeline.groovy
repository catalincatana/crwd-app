#!groovy

pipeline {
    agent any
    stages {
        stage('Start Build') {
            steps {
                build job: 'BuildCrwdApp', parameters: [string(name: 'TAG', value: "$(git rev-parse --short HEAD)")]
            }
        }
        stage('Deploy') {
            steps {
                build job: 'DeployCrwdApp'
            }
        }
    }
}
