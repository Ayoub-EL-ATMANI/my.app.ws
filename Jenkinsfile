node{
    stage('SCM Checkout'){
        git 'https://github.com/Ayoub-EL-ATMANI/my.app.ws'
    }
    stage('Compile-Package'){
        sh 'mvn package'
    }
}