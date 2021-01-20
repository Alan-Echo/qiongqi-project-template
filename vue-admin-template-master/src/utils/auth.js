import Cookies from 'js-cookie'

const TokenKey = 'X-TOKEN'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

//  =========== 清空登录信息
export function removeSite(){
  Cookies.remove(tokenKey)
  // Cookies.remove(userKey)
}





