
import {getCookie} from './cookie'
var websocket = null

export default function (Vue, options) {
  Vue.prototype.websocketObj = function (callback) {
    if (callback == null) {
      closeWebSocket()
      return
    }
    if ('WebSocket' in window) {
      websocket = new WebSocket('ws://test.iwill.beigebigdata.com/zt-api/websocket/' + sessionStorage.getItem('customerId'))
    } else {
      console.log('当前浏览器 Not support websocket')
    }
    // 连接发生错误的回调方法
    websocket.onerror = function () {
      console.log('WebSocket连接发生错误')
    }

    // 连接成功建立的回调方法
    websocket.onopen = function () {
      console.log('WebSocket连接成功')
    }

    // 连接关闭的回调方法
    websocket.onclose = function () {
      console.log('WebSocket连接关闭')
    }

    // 接收到消息的回调方法
    websocket.onmessage = function (event) {
      callback(event.data)
    }
  }
}

// 关闭WebSocket连接
function closeWebSocket () {
  websocket.close()
}
