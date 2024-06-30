// def name = "haivt"
// int age = 20
// def isEngineer = true

// def person = [name: "Haivt", age: 20]

def num = 10

if (num > 100){
    println("Value more than 100")
} else {
    println("Value less than 100")
}

def envs = ["dev"]
for (def env : envs){
    if(env == "stg"){
        println "Run sonar"
    } else {
        println "Don't run sonar"
    }
}

def runSonarQuebe(){
    println "Run Sonar"
}

def calculateSum(def a, def b){
    return a + b;
}

runSonarQuebe()
def sum = calculateSum(1, 2)
println sum

// def colors = ["Red", "Green", "Blue"]
// def listStudent = new ArrayList()

// for (def color : colors){
//     System.out.println(color)
// }

// def sayHello() {
//     println "Hello, Groovy!"
// }

// def add(int a, int b) {
//     return a + b
// }

// sayHello()
// println(add(5, 3))