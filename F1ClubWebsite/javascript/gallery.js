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
  let currentImg = 1;

    function swap() 
    {
      let imgNum = parseInt(this.id.charAt(4)); 
    
      document.querySelector('#mainImg').src = 'images/gall0' + imgNum + '.jpg';
      currentImg = imgNum;
  }
  

    function next()
    {
        if(currentImg == 5)
        {
            document.querySelector('#mainImg').src='images/gall01.jpg';
            currentImg=1;
        }
        else
        {
            currentImg++;
            document.querySelector('#mainImg').src='images/gall0' + currentImg + '.jpg';
        }

    }

    function previous() 
    {
        if (currentImg == 1) 
        {
          currentImg = 5; 
        } 
        else 
        {
          currentImg--;
        }
        document.querySelector('#mainImg').src = 'images/gall0' + currentImg + '.jpg';
      }
      
    

    document.querySelector('#link1').addEventListener('click', swap);
    document.querySelector('#link2').addEventListener('click', swap);
    document.querySelector('#link3').addEventListener('click', swap);
    document.querySelector('#link4').addEventListener('click', swap);
    document.querySelector('#link5').addEventListener('click', swap);
    document.querySelector('#prev').addEventListener('click', previous);
    document.querySelector('#next').addEventListener('click', next);

});