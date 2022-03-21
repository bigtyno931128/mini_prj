let targetId;

$(document).ready(function () {
    getBoard();
    open_box();
})

//////////////////////////////////////////////////////////////////////////////////////////
///// 여기 아래에서부터 코드를 작성합니다. ////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////

function open_box() {
    $('#contact').toggle();
}

//게시글 작성하는 함수.
function writePost() {
    let title = $('#title').val();
    let writer = $('#writer').val();
    let content = $('#content').val();

    let data = {'title': title, 'writer': writer, 'content': content};

    $.ajax({
        type: "POST",
        url: "/api/board",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('메시지가 성공적으로 작성되었습니다.');
            window.location.reload();
        }
    })
}

//게시글 전체 불러오기.
function getBoard() {

    $.ajax({
        type: "GET",
        url: "/api/board",
        success: function (response) {
            console.log(response);
            for (let i = 0; i < response.length; i++) {
                let post = response[i];
                let id = post['id'];
                let title = post['title'];
                let writer = post['writer'];
                let content = post['content'];
                let createdAt = post['createdAt'];
                addHTML(id, title, writer, content, createdAt);
            }
        }
    })
}

// 게시글 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
function addHTML(id, title, writer, content, createdAt) {
    // 1. HTML 태그를 만듭니다.
    let tempHtml = `<div class="row justify-content-center">
                        
                            <!-- date/writer/title 영역 -->
                            <div class="metadata">
                                <div id ="${id}-createAt" class="date">
                                    ${createdAt}
                                </div>
                                <div id="${id}-writer" class="username">
                                    ${writer}
                                </div>
                                <div id="${id}-title" class="username">
                                    ${title}
                                </div>
                            </div>
                            <!-- contents 조회 영역-->
                            <div class="contents">
                                <div id="${id}-content" class="text">
                                    ${content}
                                </div>
                            </div>
                        </div>
                    </div>
                    `;
    $('#portfolio').append(tempHtml);
}




