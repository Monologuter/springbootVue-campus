import request from '@/util/request'

// 查询通知公告公告列表
export function listAdvise(query) {
  return request({
    url: '/notice/advise/list',
    method: 'get',
    params: query
  })
}

// 查询通知公告公告详细
export function getAdvise(noticeId) {
  return request({
    url: '/notice/advise/' + noticeId,
    method: 'get'
  })
}

// 新增通知公告公告
export function addAdvise(data) {
  return request({
    url: '/notice/advise',
    method: 'post',
    data: data
  })
}

// 修改通知公告公告
export function updateAdvise(data) {
  return request({
    url: '/notice/advise',
    method: 'put',
    data: data
  })
}

// 删除通知公告公告
export function delAdvise(noticeId) {
  return request({
    url: '/notice/advise/' + noticeId,
    method: 'delete'
  })
}
