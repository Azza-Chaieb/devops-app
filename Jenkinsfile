pipeline {
    agent any
    
    tools {
        maven 'M3'
        jdk 'JDK11'
    }
    
    environment {
        TOMCAT_URL = 'http://localhost:8082'
    }
    
    stages {
        stage('Checkout GitHub') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/Azza-Chaieb/devops-app.git'
                sh 'echo "Code source récupéré avec succès"'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
                sh 'mvn package -DskipTests'
            }
            post {
                success {
                    echo '✅ Build Maven réussi!'
                    archiveArtifacts artifacts: 'target/*.war', fingerprint: true
                }
            }
        }
        
        stage('Tests Unitaires') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Déploiement Tomcat') {
            steps {
                script {
                    sh '''
                        set +e
                        /opt/tomcat/bin/shutdown.sh
                        sleep 5
                        rm -rf /opt/tomcat/webapps/devops-app*
                        rm -rf /opt/tomcat/webapps/ROOT*
                        cp target/devops-app.war /opt/tomcat/webapps/ROOT.war
                        /opt/tomcat/bin/startup.sh
                        sleep 10
                    '''
                }
            }
            post {
                success {
                    echo "✅ Application déployée sur ${TOMCAT_URL}"
                }
            }
        }
    }
    
    post {
        always {
            echo "Pipeline ${currentBuild.result}"
        }
        success {
            echo '🎉 Pipeline exécutée avec succès!'
        }
    }
}
