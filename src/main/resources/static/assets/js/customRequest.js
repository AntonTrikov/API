class CustomRequest{
    constructor(method,url){
        this.xhr = new XMLHttpRequest();
        this.jwttoken = 'Bearer ' + localStorage.getItem('jwttoken');
        this.xhr.withCredentials = true;
        this.xhr.open(method,url,true);
        this.xhr.setRequestHeader('Content-Type', 'application/json');
        this.xhr.setRequestHeader("Authorization", this.jwttoken);
        this.xhr.setRequestHeader("Access-Control-Allow-Credentials", 'true');
        this.xhr.setRequestHeader("Access-Control-Allow-Origin", '*');
    }

}