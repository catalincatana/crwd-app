#!groovy

pipeline {
    agent any
    stages {
        stage('Start Build') {
            steps {
                script {
                    def version = readFile('VERSION').trim()
                    build job: 'BuildCrwdApp', parameters: [string(name: 'TAG', value: version)]
                    }
            }
        }
        stage('Deploy') {
            steps {
                build job: 'DeployCrwdApp'
            }
        }
    }
}
