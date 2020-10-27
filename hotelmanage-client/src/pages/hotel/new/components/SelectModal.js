import React, { useState, PureComponent } from 'react';
import PropTypes from 'prop-types'
import { Input, Table, Modal, Space } from 'antd'
import Filter from './Filter'
import { prototype } from 'module';

class SelectModal extends PureComponent {

  constructor(props){
    super(props)
    this.state = {}
  }

  handleSelected = (item) => {
    const { onOk } = this.props
    onOk(item)
  }

  get filterProps() {
    return {
      onFilterChange: (values) => {
        const { onSearch } = this.props
        values.pageIndex = 1
        values.pageSize = 10
        this.setState(values)
        onSearch(values)
      },
    }
  }

  handlePageChange = page => {
    const { onSearch } = this.props
    let values = this.state
    values.pageIndex = page.current
    values.pageSize = page.pageSize
    onSearch(values)
  }

  render() {
    const { list, pagination, loading, onOk, i18n, ...guideModalProps } = this.props

    const columns = [
      {
        title: '成员姓名',
        dataIndex: 'name',
        key: 'name',
        width: '20%',
        render: (text, record) => {
          return (<span onClick={this.handleSelected(record)}>text</span>);
        },
      },
      {
        title: '身份证号',
        dataIndex: 'idNo',
        key: 'idNo',
        width: '40%',
        render: (text, record) => {
          return (<span onClick={this.handleSelected(record)}>text</span>);
        },
      },
      {
        title: '联系电话',
        dataIndex: 'phone',
        key: 'phone',
        width: '20%',
        render: (text, record) => {
          return (<span onClick={this.handleSelected(record)}>text</span>);
        },
      },
    ]

    return (
      <Modal {...guideModalProps} footer={null}>
        <Space direction='vertical'>
          <Filter {...this.filterProps} ></Filter>
          <Table
            loading={loading}
            columns={columns}
            dataSource={list}
            pagination={pagination}
            onChange={this.handlePageChange}
            rowClassName={record => (record.editable ? styles.editable : '')}
          />
        </Space>
      </Modal>
    )
  }

}

SelectModal.prototypes={
  list: PropTypes.array,
  pagination: PropTypes.object,
  loading: PropTypes.object,
  onSearch: PropTypes.func,
  onOk: PropTypes.func
}
export default SelectModal