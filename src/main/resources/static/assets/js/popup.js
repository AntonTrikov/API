class Popup{
    constructor(text,success){
        this.div = document.createElement("div");
        this.div.id="popup-div";
        this.div.style.display="none";
        this.closePopupButton = document.createElement("popup");
        this.closePopupButton.id="close-popup"
        this.closePopupButton.innerHTML='&times;';
        this.closePopupButton.addEventListener("click",()=>{
            this.hide();
        })
        this.div.appendChild(this.closePopupButton);
        this.setText(text);
        success? this.isSuccess() : this.isFailure();
        this.show();
    }

    show(){
        this.div.style.display="block";
        document.body.appendChild(this.div);
    }
    setText(text){
        let newNode = document.createTextNode(text);
        this.div.appendChild(newNode);
    }
    isSuccess(){
        this.div.style.borderColor="green";
        this.div.style.backgroundColor="lightgreen"
    }
    isFailure(){
        this.div.style.borderColor="red";
        this.div.style.backgroundColor="lightcoral"
    }
    hide(){
        this.div.style.display="none";
    }

}