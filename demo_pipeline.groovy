node {
  stage ('Build') {
    sh 'echo Commands to build application'
  }
  stage ('Test') {
    sh '''
    echo Commands to test application
    echo Other tests
    '''
  }
  stage ('Deploy') {
    sh 'echo Command to deploy application'
  }
}
