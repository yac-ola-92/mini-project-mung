
document.addEventListener('DOMContentLoaded', function() {
  
    const loadingHTML = `
        <div id="loading" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(175, 128, 0, 0.28); z-index: 9999;">
            <img src="https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExOTR1bnd1dXFwenptcnh3azB3eDgwNzhsaWs1cWN2eWo3bnp6aWhvayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/llmnJghXZbYGIdUlxN/giphy.webp" alt="Loading..." style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); border-radius: 50%;">
        </div>
    `;//이미지 입력할 위치 
    document.body.insertAdjacentHTML('beforeend', loadingHTML);

    const links = document.querySelectorAll('a');
    links.forEach(link => {
        link.addEventListener('click', function(event) {
            document.getElementById('loading').style.display = 'block'; 

            event.preventDefault(); 
            
            setTimeout(() => {
                window.location.href = this.href;
            }, 600);
        });
    });
});

window.addEventListener('load', function() {
    document.getElementById('loading').style.display = 'none'; 
});
