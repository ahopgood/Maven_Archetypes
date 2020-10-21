pipeline {
    agent { label 'Java11' }
    stages {
        stage('build') {
            steps {
                git credentialsId: 'github_token', url: 'https://github.com/ahopgood/Maven_Archetypes.git', branch: '${BRANCH_NAME}'
                sh 'mvn --version'
                sh 'mvn clean install'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            junit '**/target/failsafe-reports/*.xml'
            jacoco(
                  execPattern: '**/target/jacoco.exec',
                  classPattern: '**/target/classes',
                  sourcePattern: '**/src/main/java',
                  exclusionPattern: '**/src/test*'
            )
            dependencyCheckPublisher pattern: 'target/dependency-check-report.xml'
            publishHTML (target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'target/site/',
                reportFiles: 'dependency-updates-report.html,property-updates-report.html,plugin-updates-report.html',
                reportName: "Versions Report"
            ])
        }
    }
}
