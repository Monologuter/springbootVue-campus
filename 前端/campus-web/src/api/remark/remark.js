import request from '@/util/request'

// 查询remark列表
export function listRemark(query) {
  return request({
    url: '/student/remark/list',
    method: 'get',
    params: query
  })
}

// 查询remark详细
export function getRemark(id) {
  return request({
    url: '/student/remark/' + id,
    method: 'get'
  })
}

// 新增remark
export function addRemark(data) {
  return request({
    url: '/student/remark',
    method: 'post',
    data: data
  })
}

// 修改remark
export function updateRemark(data) {
  return request({
    url: '/student/remark',
    method: 'put',
    data: data
  })
}

// 删除remark
export function delRemark(id) {
  return request({
    url: '/student/remark/' + id,
    method: 'delete'
  })
}
