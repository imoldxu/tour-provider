import { Table } from 'antd';
import React, { Component } from 'react'

class TouristList extends Component {
  render() {
    const { tourist, loading } = this.props

    const columns = [
      {
        title: '成员姓名',
        dataIndex: 'name',
        key: 'name',
        width: '20%',
      },
      {
        title: '身份证号',
        dataIndex: 'idNo',
        key: 'idNo',
        width: '20%',
      },
      {
        title: '联系电话',
        dataIndex: 'phone',
        key: 'phone',
        width: '40%',
      },
    ];

    return (
        <Table
          loading={loading}
          columns={columns}
          dataSource={tourist}
          pagination={false}
        />
    )
  }
}

export default TouristList;
