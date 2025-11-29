pipeline { 
    agent any 
    tools { 
        maven 'M3' 
        jdk 'JDK11' 
    } 
    stages { 
        stage('Checkout') { 
            steps { 
                git 'https://github.com/Azza-Chaieb/devops-app.git' 
            } 
        } 
        stage('Build') { 
            steps { 
                sh 'mvn clean package' 
            } 
        } 
    } 
} 
