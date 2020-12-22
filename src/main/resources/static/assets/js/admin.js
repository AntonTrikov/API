IngredientController.loadIngredients();
let addIngredientButton = document.getElementById("add-ingredient-button");
addIngredientButton.addEventListener("click",function(event){
    IngredientController.addIngredient(event);
});