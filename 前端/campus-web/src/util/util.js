export {
    setCookie,
    getCookie,
    delCookie,
}

/**
 * 設置Cookie
 * 固定30天
 * @param value
 * @param name
 */
function setCookie(name, value) {
    var days = 30;
    var date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    var expires = "; expires=" + date.toGMTString();
    document.cookie = name + "=" + value + expires + "; path=/";
}

/**
 * 獲取Cookie
 * @param name
 */
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

/**
 * 清除Cookie
 * @param name
 */
function delCookie(name) {
    var days = 0;
    var date = new Date();
    date.setTime(date.getTime() + days);
    var expires = "; expires=" + date.toGMTString();

    document.cookie = name + "=" + expires + "; path=/";
}