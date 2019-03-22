import webobj from 'axios'
import webQs from 'qs'

var serviceUrl = '/api'
export default function (Vue, options) {
  Vue.prototype.webrequest = function (
    method,
    url,
    param,
    successcallback,
    failcallback
  ) {
    var tempSuccessCallback = function (response) {
      var tempData = response.data
      // console.log(tempData)
      if (tempData.code == '200') {
        successcallback(tempData.data)
      } else {
        failcallback(tempData)
      }
    }

    var tempFailCallback = function (error) {
      console.log(error)
      failcallback(error)
    }

    var apiUrl = serviceUrl + url
    // console.log('请求url：' + apiUrl)
    // console.log('请求参数', param ? JSON.stringify(param) : param)
    switch (method) {
      case 'get':
        webobj
          .get(apiUrl, {
            params: param
          })
          .then(tempSuccessCallback)
          .catch(tempFailCallback)
        break
      case 'put':
        webobj
          .put(apiUrl, param)
          .then(tempSuccessCallback)
          .catch(tempFailCallback)
        break
      case 'delete':
        webobj
          .delete(apiUrl, {
            params: param
          })
          .then(tempSuccessCallback)
          .catch(tempFailCallback)
        break
      case 'post':
        webobj({
          method: 'post',
          url: apiUrl,
          data: JSON.stringify(param),
          headers: {
            'content-type': 'application/json;charset=UTF-8'
          }
        })
          .then(tempSuccessCallback)
          .catch(tempFailCallback)
        break
      case 'post-form':
        webobj.post(apiUrl, webQs.stringify(param))
          .then(tempSuccessCallback)
          .catch(tempFailCallback)
        break
      default:
        break
    }
  }
}
