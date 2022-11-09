import request from '@/util/request'

// 查询学生管理列表
export function listStuage(query) {
  return request({
    url: '/student/stuage/list',
    method: 'get',
    params: query
  })
}

// 查询学生管理详细
export function getStuage(id) {
  return request({
    url: '/student/stuage/' + id,
    method: 'get'
  })
}

// 新增学生管理
export function addStuage(data) {
  return request({
    url: '/student/stuage',
    method: 'post',
    data: data
  })
}

// 修改学生管理
export function updateStuage(data) {
  return request({
    url: '/student/stuage',
    method: 'put',
    data: data
  })
}

// 删除学生管理
export function delStuage(id) {
  return request({
    url: '/student/stuage/' + id,
    method: 'delete'
  })
}
