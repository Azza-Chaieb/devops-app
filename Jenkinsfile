pipeline {
    agent any
    
    tools {
        maven 'M3'
        jdk 'JDK11'
    }
    
    environment {
        TOMCAT_URL = 'http://localhost:8082'
        SONAR_SCANNER_HOME = tool 'SonarQubeScanner'
    }
    
    stages {
        stage('Checkout GitHub') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/Azza-Chaieb/devops-app.git'
                sh 'echo "✅ Code source récupéré depuis GitHub"'
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
                    publishHTML([
                        allowMissing: true,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/site/surefire-report',
                        reportFiles: 'index.html',
                        reportName: 'Rapport Tests Unitaires'
                    ])
                }
            }
        }
        
        stage('SAST - SonarQube') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh '''
                        mvn sonar:sonar \
                        -Dsonar.projectKey=devops-app \
                        -Dsonar.projectName="DevOps Application" \
                        -Dsonar.host.url=http://localhost:9000
                    '''
                }
            }
        }
        
        stage('Déploiement Tomcat') {
            steps {
                script {
                    sh '''
                        # Arrêter Tomcat
                        set +e
                        /opt/tomcat/bin/shutdown.sh
                        sleep 5
                        
                        # Nettoyer l'ancienne déploiement
                        rm -rf /opt/tomcat/webapps/devops-app*
                        rm -rf /opt/tomcat/webapps/ROOT*
                        
                        # Déployer la nouvelle version
                        cp target/devops-app.war /opt/tomcat/webapps/ROOT.war
                        
                        # Redémarrer Tomcat
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
            echo "🏁 Pipeline ${currentBuild.result} - Voir détails: ${env.BUILD_URL}"
        }
        success {
            echo '🎉 Pipeline CI/CD exécutée avec succès!'
        }
        failure {
            echo '❌ Pipeline CI/CD a échoué - Vérifiez les logs'
        }
    }
}