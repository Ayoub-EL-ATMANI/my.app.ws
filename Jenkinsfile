node {
    try {
        stage('SCM Checkout') {
            git branch: 'your_branch', url: 'https://github.com/Ayoub-EL-ATMANI/my.app.ws'
        }

        stage('Compile-Package') {
            def mvnHome = tool name: 'maven-3.6.3', type: 'maven'
            sh "${mvnHome}/bin/mvn package"
        }
    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        echo "Error: ${e.message}"
        throw e
    }
}