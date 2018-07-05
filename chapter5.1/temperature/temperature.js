console.log("script running!");
let convertButton = document.querySelector("#submit");
let showResult = document.querySelector("#result");
let convertButton2 = document.querySelector("#submit2");
let showResult2 = document.querySelector("#result2");

convertButton.addEventListener("click", e => {
    let fahrenheit = document.querySelector("#input").value;
    let celsius = ((fahrenheit * 9) / 5) + 32;
    let answer = celsius + " degrees Fahreneheit";
    showResult.innerHTML = answer;
    return false;
});

convertButton2.addEventListener("click", e => {
    let celsius = document.querySelector("#input2").value;
    let fahrenheit = (celsius - 32) * (5 / 9);
    let answer = fahrenheit + " degrees Celsius";
    showResult2.innerHTML = answer;
    return false;
})