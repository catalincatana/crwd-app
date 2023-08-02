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
                script {
                    def version = readFile('VERSION').trim()
                    build job: 'DeployCrwdApp', parameters: [string(name: 'Version', value: version), string(name: 'Env', value: 'All')]
                }
            }
        }
        // If we use this, we will enter a loop of deployments
//        stage('Increment Version') {
//            steps {
//                    // read VERSION file and increment the version
//                    script {
//                        def version = readFile('VERSION').trim()
//                        def versionParts = version.split('\\.')
//                        def major = versionParts[0].toInteger()
//                        def minor = versionParts[1].toInteger()
//                        def patch = versionParts[2].toInteger()
//                        patch++
//                        def newVersion = "${major}.${minor}.${patch}"
//                        writeFile file: 'VERSION', text: newVersion
//                        echo "New version is ${newVersion}"
//                        withCredentials([sshUserPrivateKey(credentialsId: 'GITHUB_SSH_KEY', keyFileVariable: 'GITHUB_SSH_KEY')]) {
//
//                            sh 'git config --global user.email "Admin@me.com"\n' +
//                                '  git config --global user.name "Admine Me"'
//                            sh 'git add .; git commit -m "version increased";'
//                            sh 'GIT_SSH_COMMAND="ssh -i $GITHUB_SSH_KEY" git push origin HEAD:main '
//                        }
//                }
//            }
//        }
    }
}
