const apiUrl = "http://localhost:8080/auth/register";
const form = document.getElementById("userForm");
const usernameInput = document.getElementById("username");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");
const phoneInput = document.getElementById("phone");
const submitBtn = document.getElementById("submitBtn");
const searchInput = document.createElement("input");
let editId = null;
let allUsers= [];
let sortDirection = 1;


const userTypeError = document.getElementById("userTypeError");

function validateUsername() {
  if (username.value.trim().length >= 5) {
    username.classList.add("is-valid");
    username.classList.remove("is-invalid");
    return true;
  } else {
    username.classList.add("is-invalid");
    username.classList.remove("is-valid");
    return false;
  }
}

function validatePassword() {
  const passVal = password.value;
  const passRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,}$/;
  if (passRegex.test(passVal)) {
    password.classList.add("is-valid");
    password.classList.remove("is-invalid");
    return true;
  } else {
    password.classList.add("is-invalid");
    password.classList.remove("is-valid");
    return false;
  }
}
function validatePhone() {
  const phoneVal = phoneInput.value.trim();
  const phoneRegex = /^[987]\d{9}$/;
  if (phoneRegex.test(phoneVal)) {
    phoneInput.classList.add("is-valid");
    phoneInput.classList.remove("is-invalid");
    return true;
  } else {
    phoneInput.classList.add("is-invalid");
    phoneInput.classList.remove("is-valid");
    return false;
  }
}
function validateEmail() {
    const emailVal = emailInput.value.trim();
  
    if (!emailVal || !emailInput.checkValidity()) {
      emailInput.classList.add("is-invalid");
      emailInput.classList.remove("is-valid");
      return false;
    }

    fetch(`${apiUrl}?email=${encodeURIComponent(emailVal)}`)
      .then(res => res.json())
      .then(data => {
        if (data.length > 0 && (!editId || data[0].id !== editId)) {
          emailInput.classList.add("is-invalid");
          emailInput.classList.remove("is-valid");
        } else {
          emailInput.classList.add("is-valid");
          emailInput.classList.remove("is-invalid");
        }
      });
  
    return true;
  
    }

function validateUserType() {
  const userType = document.querySelector('input[name="usertype"]:checked');
  if (!userType) {
    userTypeError.classList.remove("d-none");
    return false;
  } else {
    userTypeError.classList.add("d-none");
    return true;
  }
}

// Attach blur event handlers
username.addEventListener("blur", validateUsername);
password.addEventListener("blur", validatePassword);
phoneInput.addEventListener("blur", validatePhone);
email.addEventListener("blur", validateEmail);

// Submit Handler
form.addEventListener("submit", function (e) {
  e.preventDefault();


  if (valid) {
    form.reset();
    document.querySelectorAll(".form-control").forEach(el => el.classList.remove("is-valid"));
  }
});

form.addEventListener("submit", function (e) {
    e.preventDefault();
    const valid = validateUsername() & validatePassword() & validateEmail() & validatePhone();
   if (!valid) return;

    const users = {
        name: usernameInput.value.trim(),
        email: emailInput.value.trim(),
        password: passwordInput.value.trim(),
        phone: phoneInput.value.trim(),
        usertype: "user"
    }

    if (editId) {
        fetch({apiUrl}/{editId}, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(users)
        }).then(() => {
            //fetchAndRenderUsers();
            form.reset();
            editId = null;
            submitBtn.textContent = "Add User";
            clearValidation();
        });
    } else {
        fetch(apiUrl, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(users)
        }).then(() => {
            //fetchAndRenderUsers();
            form.reset();
            clearValidation();
        });
    }
    if (valid) {
        form.reset();
        document.querySelectorAll(".form-control").forEach(el => el.classList.remove("is-valid"));
      }
});

function toLogin() {
  window.location.href = "Login.html";
}


