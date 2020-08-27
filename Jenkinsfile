pipeline {
    agent { label 'Java11' }
    stages {
        stage('build') {
            steps {
                git credentialsId: 'github_token', url: 'https://github.com/ahopgood/Maven_Archetypes.git', branch: '${BRANCH_NAME}'
                sh 'mvn --version'
                sh 'mvn clean install -pl Hibernate_Archetype,Java7,Java8,Spring-Boot_Archetype,Flyway_Archetype'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            jacoco(
                  execPattern: '**/target/jacoco.exec',
                  classPattern: '**/target/classes',
                  sourcePattern: '**/src/main/java',
                  exclusionPattern: '**/src/test*'
            )
        }
    }
}
