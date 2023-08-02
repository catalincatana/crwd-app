#!groovy

pipeline {
    agent any
    stages {
        stage('Start Build') {
            steps {
                sh 'echo "Hello World!"'
            }
        }
    }
}
