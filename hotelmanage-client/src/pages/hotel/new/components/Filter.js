import React, { Component } from 'react'
import PropTypes from 'prop-types'
import { FilterItem } from 'components'

import { Trans } from '@lingui/react'
import { Button, Row, Col, DatePicker, Form, Input, Cascader } from 'antd'
const { RangePicker } = DatePicker

const ColProps = {
  // xs: 24,
  // sm: 12,
  // style: {
  //   marginBottom: 16,
  // },
}

const TwoColProps = {
  ...ColProps,
  // xl: 96,
}

class Filter extends Component {
  formRef = React.createRef()

  // handleFields = fields => {
  //     const { createTime } = fields
  //     if (createTime.length) {
  //       fields.createTime = [
  //         moment(createTime[0]).format('YYYY-MM-DD'),
  //         moment(createTime[1]).format('YYYY-MM-DD'),
  //       ]
  //     }
  //     return fields
  // }

  handleSubmit = values => {
    const { onFilterChange } = this.props
    // 删除没有设置的字段
    // for (let item in values){
    //   if (values[item] == undefined){
    //     delete values[item]
    //   } 
    // }
    console.log(values)
    onFilterChange(values)
  }

  handleReset = () => {
    const { getFieldsValue, setFieldsValue } = this.formRef.current

    const fields = getFieldsValue()
    for (let item in fields) {
      if ({}.hasOwnProperty.call(fields, item)) {
        if (fields[item] instanceof Array) {
          fields[item] = []
        } else {
          fields[item] = undefined
        }
      }
    }
    setFieldsValue(fields)
    this.handleSubmit(fields)
  }
  
  handleChange = (key, values) => {
    const { onFilterChange } = this.props
    let fields = this.formRef.current.getFieldsValue()
    fields[key] = values
    //fields = this.handleFields(fields)
    onFilterChange(fields)
  }

  render() {
    const { i18n } = this.props

    return (
      <Form
        ref={this.formRef}
        name="control-ref"
        onFinish={this.handleSubmit}
        onReset={this.handleReset}
        
      >
        <Row gutter={16}>
          <Col {...ColProps} xl={{ span: 6 }} md={{ span: 10 }}>
            <Form.Item
              name="idNo"
            >
              <Input
                placeholder="请输入证件号"
              />
            </Form.Item>
          </Col>
          <Col
            {...ColProps}
            xl={{ span: 6 }}
            md={{ span: 10 }}
          >
            <Form.Item name="name">
              <Input
                placeholder="请输入姓名"
              />
            </Form.Item>
          </Col>
          <Col
            {...ColProps}
            xl={{ span: 6 }}
            md={{ span: 10 }}
          >
            <Form.Item name="phone">
              <Input
                placeholder="请输入电话号码"
              />
            </Form.Item>
          </Col>
          <Col
            {...TwoColProps}
            xl={{ span: 6 }}
            md={{ span: 24 }}
            sm={{ span: 24 }}
          >
            <Row type="flex" align="middle" justify="space-between">
              <div>
                <Button
                  type="primary"
                  className="margin-right"
                  htmlType="submit"
                >
                  <Trans>Search</Trans>
                </Button>
                <Button
                  htmlType="reset">
                  <Trans>Reset</Trans>
                </Button>
              </div>
              {/* <Button type="ghost" onClick={ ()=> onAdd() }>
                  <Trans>Create</Trans>
                </Button> */}
            </Row>
          </Col>
        </Row>
      </Form>
    )
  }

}

Filter.propTypes = {
  filter: PropTypes.object,
  onFilterChange: PropTypes.func,
}

export default Filter