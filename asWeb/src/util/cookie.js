//  @desc 根据name读取cookie
//  @param  {String} name
//  @return {String}
function getCookie (name) {
  var arr = document.cookie.replace(/\s/g, '').split(';')
  for (var i = 0; i < arr.length; i++) {
    var tempArr = arr[i].split('=')
    if (tempArr[0] == name) {
      return decodeURIComponent(tempArr[1])
    }
  }
  return ''
}

//  @desc  设置Cookie
//  @param {String} name
//  @param {String} value
//  @param {Number} days
function setCookie (name, value, days) {
  var date = new Date()
  date.setDate(date.getDate() + days)
  document.cookie = name + '=' + value + ';expires=' + date
}

//  @desc 根据name删除cookie
//  @param  {String} name
function removeCookie (name) {
  // 设置已过期，系统会立刻删除cookie
  // var date = new Date();
  // date.setDate(date.getDate() + -1);
  // document.cookie = name + '=' + '1' + ';expires=' + date;

  var exp = new Date()
  exp.setTime(exp.getTime() - 1)
  var cval = getCookie(name)
  if (cval != null) { document.cookie = name + '=' + cval + ';expires=' + exp.toGMTString() }
}

// 时间日期格式化
const format = function (time, format) {
  var t = new Date(time)
  var tf = function (i) { return (i < 10 ? '0' : '') + i }
  return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function (a) {
    switch (a) {
      case 'yyyy':
        return tf(t.getFullYear())
        break
      case 'MM':
        return tf(t.getMonth() + 1)
        break
      case 'mm':
        return tf(t.getMinutes())
        break
      case 'dd':
        return tf(t.getDate())
        break
      case 'HH':
        return tf(t.getHours())
        break
      case 'ss':
        return tf(t.getSeconds())
        break
    }
  })
}

export {getCookie, setCookie, removeCookie, format}
