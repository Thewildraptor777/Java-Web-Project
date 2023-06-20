function wait3Seconds() {
    const startTime = new Date().getTime();
    let currentTime = startTime;
    while(currentTime - startTime < 5000) {
      currentTime = new Date().getTime();  
    }
  }
  
  // Call the function
  
  console.log("3 seconds have passed!");
    if (document.getElementById("wronguser").innerHTML=="true"){
        document.body.innerHTML="please enter a correct username or password" ;
         wait3Seconds();

        window.location.replace("/")
    }
    if (document.getElementById("usercreation").innerHTML=="true"){
window.location.replace("signup")    }
