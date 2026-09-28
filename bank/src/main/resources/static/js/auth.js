const bloodcareAuth =
    sessionStorage.getItem("bloodcareAuth");


/* ================= LOGIN CHECK ================= */

if (!bloodcareAuth) {

    window.location.href =
        "/login.html";

}


/* ================= API FETCH ================= */

async function apiFetch(url, options = {}) {

    options.headers = {

        ...(options.headers || {}),

        "Authorization":
            "Basic " + bloodcareAuth

    };


    const response =
        await fetch(url, options);


    /* SESSION EXPIRED */

    if (response.status === 401) {

        sessionStorage.clear();

        window.location.href =
            "/login.html";

        return;

    }


    /* NO PERMISSION */

    if (response.status === 403) {

        alert(
            "You do not have permission to access this page."
        );

    }


    return response;

}


/* ================= LOGOUT ================= */

function logout() {

    sessionStorage.removeItem(
        "bloodcareAuth"
    );

    sessionStorage.removeItem(
        "bloodcareUsername"
    );

    sessionStorage.removeItem(
        "bloodcareRole"
    );


    window.location.href =
        "/login.html";

}


/* ================= DISPLAY USERNAME ================= */

function displayUsername() {

    const username =
        sessionStorage.getItem(
            "bloodcareUsername"
        );


    const element =
        document.getElementById(
            "loggedInUsername"
        );


    if (
        element &&
        username
    ) {

        element.textContent =
            username;

    }

}