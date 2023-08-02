#!groovy

pipeline {
    agent any
    stages {
//        stage('Start Build') {
//            steps {
//                script {
//                    def version = readFile('VERSION').trim()
//                    build job: 'BuildCrwdApp', parameters: [string(name: 'TAG', value: version)]
//                    }
//            }
//        }
//        stage('Deploy') {
//            steps {
//                build job: 'DeployCrwdApp'
//            }
//        }
        stage('Increment Version') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'GITHUB_SSH_KEY', keyFileVariable: 'GITHUB_SSH_KEY')]) {
                    sh 'echo ${GITHUB_SSH_KEY} > ~/.ssh/id_rsa'
                    sh 'chmod 600 ~/.ssh/id_rsa'
                    sh 'cat ~/.ssh/id_rsa'
                }
                // read VERSION file and increment the version
                script {
                    def version = readFile('VERSION').trim()
                    def versionParts = version.split('\\.')
                    def major = versionParts[0].toInteger()
                    def minor = versionParts[1].toInteger()
                    def patch = versionParts[2].toInteger()
                    patch++
                    def newVersion = "${major}.${minor}.${patch}"
                    writeFile file: 'VERSION', text: newVersion
                    echo "New version is ${newVersion}"
                    sh 'git config --global user.email "Admin@me.com"\n' +
                            '  git config --global user.name "Admine Me"'
                    sh 'git add .; git commit -m "version increased"; git push origin HEAD:main'
                }
            }
        }
    }
}
