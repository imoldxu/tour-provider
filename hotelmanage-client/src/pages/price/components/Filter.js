import React, { Component } from 'react'
import PropTypes from 'prop-types'
import moment from 'moment'
import { FilterItem } from 'components'

import { Trans } from '@lingui/react'
import { Button, Row, Col, DatePicker, Form, Input, Cascader, Select } from 'antd'
const { RangePicker } = DatePicker
const { Option } = Select;

const ColProps = {
  // xs: 24,
  // sm: 12,
  // style: {
  //   marginBottom: 12,
  // },
}

const TwoColProps = {
  ...ColProps,
  // xl: 96,
}

class Filter extends Component {
  formRef = React.createRef()

  handleFields = fields => {

    const {day} = fields;
    fields.date = moment(day).format('YYYY-MM-DD');
    delete fields.day;

    return fields
  }

  handleSubmit = values => {
    const { onFilterChange } = this.props
    let fields = this.handleFields(values)
    onFilterChange(fields)
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
    fields = this.handleFields(fields)
    onFilterChange(fields)
  }

  render() {
    const { filter, i18n } = this.props
    const { name, city, xclv, lv, area, date } = filter

    let day = moment(date)
    
    return (
      <Form
        ref={this.formRef}
        name="priceQuery"
        layout="horizontal"
        initialValues={{name, city, xclv, lv, area, day}}
        onFinish={this.handleSubmit}
        onReset={this.handleReset}
      >
        <Row gutter={16}>
          <Col {...ColProps} span= "6">
            <Form.Item label="酒店名称" name='name'>
              <Input
                placeholder="请输入酒店名称"
              />
            </Form.Item>
          </Col>
          <Col
            {...ColProps} span= "6"
          >
            <Form.Item
              name='city' label="城市">
              <Select>
                <Option value="成都">成都</Option>
                <Option value="九寨沟">九寨沟</Option>
                <Option value="峨眉">峨眉</Option>
              </Select>
            </Form.Item>
          </Col>
          <Col
            {...ColProps} span= "6"
          >
            <Form.Item name="xclv" label="携程等级">
              <Select>
                <Option value="2钻">2钻</Option>
                <Option value="3钻">3钻</Option>
                <Option value="4钻">4钻</Option>
                <Option value="5钻">5钻</Option>
              </Select>
            </Form.Item>
          </Col>
          <Col
            {...ColProps} span= "6"
          >
            <Form.Item name="lv" label="标准等级">
              <Select>
                <Option value="准3">准3</Option>
                <Option value="挂3">挂3</Option>
                <Option value="准4">准4</Option>
                <Option value="挂4">挂4</Option>
                <Option value="准5">准5</Option>
                <Option value="挂5">挂5</Option>
              </Select>
            </Form.Item>
          </Col>
          <Col
            {...ColProps} span= "6"
          >
            <Form.Item name="area" label="区域商圈">
              <Select>
                <Option value="春熙路">春熙路</Option>
                <Option value="宽窄巷子">宽窄巷子</Option>
                <Option value="杜甫草堂">杜甫草堂</Option>
                <Option value="武侯祠">武侯祠</Option>
                <Option value="文殊院">文殊院</Option>
                <Option value="一环内">一环内</Option>
                <Option value="二环内">二环内</Option>
                <Option value="三环内">三环内</Option>
              </Select>
            </Form.Item>
          </Col>
          <Col
            {...ColProps} span="6"
          >
            <Form.Item name="canForeign" label="是否涉外">
              <Select>
                <Option value="true">是</Option>
                <Option value="false">否</Option>
              </Select>
            </Form.Item>
          </Col>
          <Col
            {...ColProps} span="6"
          >
            <Form.Item name="date" label="日期"
              rules={[{ required: true }]}
              hasFeedback
              >
              <DatePicker format="yyyy-MM-DD">
              </DatePicker>
            </Form.Item>
          </Col>
          <Col {...ColProps} span="6" >
            <Row justify="space-around">
              <Button
                type="primary"
                className="margin-right"
                htmlType="submit">
                <Trans>Search</Trans>
              </Button>
              <Button
                htmlType="reset" >
                <Trans>Reset</Trans>
              </Button>
            </Row>
          </Col>
        </Row>
      </Form>
    )
  }

}

Filter.PropTypes = {
  filter: PropTypes.object,
  onFilterChange: PropTypes.func,
}

export default Filter