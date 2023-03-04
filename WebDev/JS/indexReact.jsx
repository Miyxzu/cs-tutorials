console.log('Hello World')
// window.alert('Any gifters?')
let name = 'updated';
console.log(name);

let bool = true;
console.log(bool);

let num = 20;
console.log(num + " + 1 = ");
num++;
console.log(num);

console.log("Hello", name);
console.log("Num =", num);
console.log("Check:", bool);

document.getElementById("p1").innerHTML = "Hello " + name;
document.getElementById("p2").innerHTML = "Num = " + num;
document.getElementById("p3").innerHTML = "Check: " + bool;