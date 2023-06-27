
// Call the function

if (document.getElementById("wronguser").innerHTML == "true") {
  document.body.innerHTML = "please enter a correct username or password";
  window.location.replace("/")
}
if (document.getElementById("usercreation").innerHTML == "true") {
  window.location.replace("/signup")
}
