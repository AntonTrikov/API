let jwttoken = 'Bearer ' + localStorage.getItem('jwttoken')
console.log(jwttoken);
let xhr = new XMLHttpRequest();
xhr.withCredentials = true;
xhr.open("GET",'http://localhost:8888/ingredient');
xhr.setRequestHeader('Content-Type', 'application/json');
xhr.setRequestHeader("Authorization", jwttoken);
xhr.setRequestHeader("Access-Control-Allow-Credentials", 'true');
xhr.setRequestHeader("Access-Control-Allow-Origin", '*');
xhr.send();
xhr.onreadystatechange = function() {
    console.log(xhr.readyState)
}

/*fetch('http://localhost:8888/ingredient',{
    method: 'GET',
    withCredentials: true,
    credentials: 'include',
    headers: {
        'Content-Type': 'application/json',
        'Authorization':'Bearer '
      }
  })
.then(response => response.json())
.then(data => {
    console.log(data);
})
.catch((error) => {
    console.error('Error:', error);
  })*/