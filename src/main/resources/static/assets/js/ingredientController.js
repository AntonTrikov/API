class IngredientController{
    static addIngredient(event){
        console.log(event);
        event.preventDefault();
        let newIngredientName = document.getElementById("new-ingredient-name").value;
        let newIngredientAllergenic = document.getElementById("new-ingredient-allergenic").checked;
        console.log(newIngredientName + newIngredientAllergenic)
        let newIngredient = {
            "name":newIngredientName,
            "allergenic":newIngredientAllergenic
        };
        
        let request = new CustomRequest("POST",'http://localhost:8888/ingredient');
        request.xhr.onreadystatechange  = function(){
            if(request.xhr.readyState === 4){
                console.log("xhr status" +request.xhr.status )
                if(request.xhr.status === 201){
                    let popup = new Popup("succesfully create ingredient "+newIngredientName,true);
                    IngredientController.loadIngredients();
                }else{
                    console.log(request.xhr);
                    let text = "there has been an error";
                        text += JSON.parse(request.xhr.responseText).errors;
                        let popup = new Popup(text,false);
                }
            }
    
            }
        ;
        request.xhr.send(JSON.stringify(newIngredient));
    
    };
    static loadIngredients(){
        let request = new CustomRequest("GET",'http://localhost:8888/ingredient');
        request.xhr.send();
        request.xhr.onreadystatechange = function(){
            if(request.xhr.readyState === 4){
                if(request.xhr.status === 200){
                    let ingredients = JSON.parse(request.xhr.responseText);
                    this.table = document.getElementById("ingredient-table");
                    var rowCount = this.table.rows.length;
                    while(--rowCount){
                        this.table.deleteRow(rowCount);
                    } 
                    ingredients.forEach(ingredient => IngredientController.addIngredientsToDom(ingredient) ); ;
                }else if (request.xhr.status === 401){
                    let popup = new Popup("your session has expired, you are begin redirected to login",false);
                    setTimeout(function(){
                        location.href = 'index';
                     }, 3000);
                }
            }
            
        }
    }

    static addIngredientsToDom(ingredient){
        let ingredientName = document.createElement("p");
        ingredientName.innerHTML=ingredient.name;
        let allergenicCheckbox = document.createElement("input");
        allergenicCheckbox.setAttribute("type","checkbox");
        allergenicCheckbox.checked=ingredient.allergenic;
        let actionsContainer = document.createElement("div");
        actionsContainer.id = "actions-container";
        let updateButton = document.createElement("i");
        updateButton.classList.add("fa");
        updateButton.classList.add("fa-floppy-o");
        updateButton.addEventListener("click",function(){
            let ingredientObj = {
                "name":ingredient.name,
                "allergenic":allergenicCheckbox.checked
            };
            let request = new CustomRequest("PUT",'http://localhost:8888/ingredient/'+ingredient.id);
            request.xhr.send(JSON.stringify(ingredientObj));
            request.xhr.onreadystatechange  = function(){
                if(request.xhr.readyState === 4){
                    console.log("xhr status" +request.xhr.status )
                    if(request.xhr.status === 200){
                        let popup = new Popup("succesfully updated ingredient " + ingredient.name,true);
                        IngredientController.loadIngredients();
                    }else{
                        console.log(request.xhr);
                        let text = "there has been an error";
                            text += JSON.parse(request.xhr.responseText).errors;
                            let popup = new Popup(text,false);
                    }
                }
        
                }

        })
        let removeButton = document.createElement("i");
        removeButton.classList.add("fa");
        removeButton.classList.add("fa-trash");
        removeButton.addEventListener("click",function(){
            let request = new CustomRequest("DELETE",'http://localhost:8888/ingredient/'+ingredient.id);
            request.xhr.send(JSON.stringify(ingredient));
            request.xhr.onreadystatechange  = function(){
                if(request.xhr.readyState === 4){
                    console.log("xhr status" +request.xhr.status )
                    if(request.xhr.status === 200){
                        let popup = new Popup("succesfully deleted ingredient " + ingredient.name,true);
                        IngredientController.loadIngredients();
                    }else{
                        console.log(request.xhr);
                        let text = "there has been an error";
                            text += JSON.parse(request.xhr.responseText).errors;
                            let popup = new Popup(text,false);
                    }
                }
        
                }

        })
        actionsContainer.appendChild(updateButton);
        actionsContainer.appendChild(removeButton);
        let row = document.getElementById("ingredient-table").insertRow(1);
        let cell1 = row.insertCell(0);
        let cell2 = row.insertCell(1);
        let cell3 = row.insertCell(2);
        cell1.appendChild(ingredientName);
        cell2.appendChild(allergenicCheckbox);
        cell3.appendChild(actionsContainer);
    }
}