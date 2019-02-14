function scrolled(div) {
    let top = $(window).scrollTop();
    let bottom = top + $(window).height();
    let position = div.offset().top;
    let total = $(window).height();
    let covered = bottom - position;
    if (covered < total) {
        let delta = parseInt(100 * covered / total);
        return (delta < 0 ? 0 : delta)
    }
    return 100;
}
function spy() {
    let bar = $('#s-spy').append('<div class="s-bar"></div>')
    $('#s-spy .s-bar').append("<div class='s-box'></div>");
    let count = 1;
    $('#s-container div').each(function(i, div){
        let html = `<div class='s-line'>
            <div id='s-${$(div).attr('id')}-solidline' class='s-solidline'></div>
            <div id='s-${$(div).attr('id')}-dashedline' class='s-dashedline'></div>
        </div><div class='s-box'></div>`
        $('#s-spy .s-bar').append(html);
        count++;
    })
    
    $('.s-line').css('width', 100/count+"%");
    $(window).scroll(function () {
        $('#s-container div').each(function(i, div){
            let first = scrolled($(div));
            $(`#s-${$(div).attr('id')}-solidline`).css('width', first + '%');
            $(`#s-${$(div).attr('id')}-dashedline`).css('width', 100 - first + '%');                    
        })
    });
}