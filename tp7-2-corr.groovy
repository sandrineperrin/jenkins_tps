node {

  stage('Gestion erreur'){

    println "Commande avant le try/catch"
    try {

      println "Commande pouvant générer une erreur"
      def number = env.BUILD_NUMBER as Integer

      if (number%2) {
        //println "Number " + number +  " is impair"
        print_result(number, "impair")
        // levee une exception
        throw new Exception()
      }
      else { 
        // println "Number "+ number +" is PAIR" 
        print_result(number, "pair")
      }

    } catch (Exception e) {
        // println "FAIL : la commande échoue, traiter ce cas"
        fail_message()
    } finally {
        println "Commande toujours exécutée"
    }
  }

  stage('End'){ println 'END'}
}


def print_result(Integer nb, String var){
  println "Number " + nb +  " is " + var
}

def fail_message(){
  print "FAIL execution : " + BUILD_NUMBER + ": job " + JOB_NAME + " cause: nombre impair"
}
