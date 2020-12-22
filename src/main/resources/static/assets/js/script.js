const loginButton = document.getElementById("login-button")
const usernameInput = document.getElementById("username-input")
const passwordInput = document.getElementById("password-input")
loginButton.addEventListener("click", function(){
    event.preventDefault();
    let user = {
        "username":usernameInput.value,
        "password":passwordInput.value
    };
    console.log(JSON.stringify(user))
    fetch('http://localhost:8888/authenticate',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
          },
        body: JSON.stringify(user),
      })
    .then(response => response.json())
    .catch((error) => {
        console.error('Error:', error);
      })
    .then(data => {
        addJwtTokenToStorage(data.jwttoken);
        redirectTo(data.role)
    })

})

function addJwtTokenToStorage(jwttoken){
    localStorage.setItem('jwttoken', jwttoken)
}
function redirectTo(userRole){
    switch(userRole){
        case 'ADMIN':
            window.location.href = "html/admin.html";
            break;
        case 'CLIENT':
            window.location.href = "html/client.html";
            break;
        case 'RESTAURANT-OWNER':
            window.location.href = "html/restaurant-owner.html";
            break;
        default:
            console.log("the user role is not managed")

    }
}