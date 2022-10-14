function confirmPassword() {
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirmPassword").value;
    let text;
    if (password!=confirmPassword) {
        text = "Senhas não são iguais.";
    }
    document.getElementById("errorMessages").innerHTML = text;
}