function checkPassword() {
    var password = document.getElementById("password").value;

    // 서버에 비밀번호 확인 요청 (AJAX를 통해 처리)
    fetch('/post/checkPassword', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ password: password, post_id: post_id })
    }).then(response => response.json())
        .then(data => {
            if (data.valid) {
                document.getElementById("submitBtn").disabled = false;  // 비밀번호가 맞으면 삭제 버튼 활성화
                alert('비밀번호가 확인되었습니다.');
            } else {
                alert('비밀번호가 틀렸습니다.');
            }
        });
}