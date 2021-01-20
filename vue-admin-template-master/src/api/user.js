import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/project/auth/login',
    method: 'post',
    params: data
  })
}

export function getInfo(token) {
  return request({
    url: '/admin/info',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/project/auth/logout',
    method: 'post'
  })
}
