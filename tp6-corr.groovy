//
// 1 - Variable d'environnement
//
node {

//  stage('0- clean'){
  //  deleteDir()
  //  checkout scm
 // }

  stage ('1- Print Jenskins variables'){
    echo "$env.VAR_GLOBAL"
  }
  stage ('1- Print all env'){
    echo 'Affiche toutes les variables environnement disponibles :'
    sh 'env'
  }
}

//
// 2 - Paramètres utilisateurs du script
//

node {
  stage('2- Print parameter'){
    sh '''
       echo "Affichage du paramètre saisie par l'utilisateur"
       echo "  valeur du paramètre : $tp6"
    '''

    // Pour récupérer la valeur dans le script
    def value = params.tp6
    def value_upper = params.tp6.toUpperCase()

    println "Print default => " + params.tp6
    println "Print default => " + value
    println "Print upper case value =>  $value_upper"

  }
}

//
// 3 - Credentials
//

node {
  stage('3- Récupération des crédentials'){
    withCredentials([
      usernamePassword(
          credentialsId: 'github_acces',
          usernameVariable: "DEMO_USERNAME",
          passwordVariable: "DEMO_PASS"
      ),
      file(
        credentialsId: 'demo_secret_file',
        variable: 'SECRET_FILE')
    ]){

      // Affichage des variables :
        print "Credential user-password : user $DEMO_USERNAME , pssword  $DEMO_PASS"
        print "Credential secret file $SECRET_FILE"
        sh "cp $SECRET_FILE toto"
        sh "less toto"
    }
  } // end withCredential, les variables ne sont plus accessibles après
}
