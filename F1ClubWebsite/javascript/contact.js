function myFunction() 
{
    let x = document.querySelector("#myTopnav");

    if(x.className === "topnav") 
    { 
        x.className += " responsive";
    } 
    else 
    {
        x.className = "topnav";
    }
}



document.addEventListener('DOMContentLoaded', function() 
{
    document.querySelector('#form1').addEventListener('submit', processForm);
});

function validate() 
{
    let email_val = document.querySelector('#email1').value;
    let email_val2 = document.querySelector('#email2').value;
    let mobile_val = document.querySelector('#mobile').value;

    if (email_val === '') 
    {
        alert("Please enter your email");
        return false;
    }

    if (email_val2 === '') 
    {
        alert("Please confirm your email");
        return false;
    }

    if (validateEmail(email_val) == false) 
    {
        alert('Invalid email format for email1');
        return false;
    }

    if (validateEmail(email_val2) == false) 
    {
        alert('Invalid email format for email2');
        return false;
    }

    if (email_val !== email_val2) 
    {
        alert('Emails do not match');
        return false;
    }

    if(validateMobile(mobile_val) == false)
    {
        alert('Mobile must be 10 characters long and start with 087, 086 or 085');
        return false;
    }
}

function processForm(event) 
{
    if (validate() == false) 
    {
        event.preventDefault();
    }
}

function validateEmail(emailAddress) 
{
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    return emailPattern.test(emailAddress);
}


function validateMobile(mobilePhone) 
{
    const mobilePattern = /^(087|086|085)\d{7}$/;
    return mobilePattern.test(mobilePhone);
}