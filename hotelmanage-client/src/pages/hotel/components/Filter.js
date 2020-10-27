import React, { Component } from 'react'
import PropTypes from 'prop-types'
import moment from 'moment'
import { FilterItem } from 'components'

import { Trans } from '@lingui/react'
import { Button, Row, Col, DatePicker, Form, Input, Cascader, Select } from 'antd'
const { RangePicker } = DatePicker
const { Option } = Select;

const ColProps = {
  //xs: 24,
  //sm: 12,
  //xl: 6,
  //md: 6,
  // style: {
  //   marginBottom: 8,
  // },
  span: 6
}

const TwoColProps = {
  // xs: 24,
  // sm: 24,
  // xl: 12,
  // md: 12,
  // style: {
  //   marginBottom: 8,
  // },
  span: 12
}

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
};
const tailLayout = {
  wrapperCol: { offset: 8, span: 16 },
};

class Filter extends Component {
  formRef = React.createRef()

  handleFields = fields => {
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
    const { filter, onAdd, i18n } = this.props
    const { name, city, xclv, lv, area } = filter

    return (
      <Form
        {...layout}  
        ref={this.formRef}
        name="hotelQuery"
        initialValues={{name, city, xclv, lv, area}}
        onFinish={this.handleSubmit}
        onReset={this.handleReset}
      >
        <Row gutter={24}>
          <Col {...ColProps}>
            <Form.Item name="name" label="酒店名称" >
              <Input
                placeholder="请输入酒店名称"
              />
            </Form.Item>
          </Col>
          <Col
            {...ColProps}
          >
            <Form.Item name="city" label="城市">
              <Select>
                <Option value="成都">成都</Option>
                <Option value="九寨沟">九寨沟</Option>
                <Option value="峨眉">峨眉</Option>
              </Select>
            </Form.Item>
          </Col>
          <Col
            {...ColProps}
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
            {...ColProps}
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
            {...ColProps}
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
            {...ColProps}
          >
            <Form.Item name="canForeign" label="是否涉外">
              <Select>
                <Option value="true">是</Option>
                <Option value="false">否</Option>
              </Select>
            </Form.Item>
          </Col>
          <Col
            {...TwoColProps}
          >
            <Row type="flex" align="middle" justify="space-between">
              <div>
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
              </div>
              <Button type="ghost" onClick={() => onAdd()}>
                <Trans>Create</Trans>
              </Button>
            </Row>
          </Col>
        </Row>
      </Form>
    )
  }

}

Filter.propTypes = {
  onAdd: PropTypes.func,
  filter: PropTypes.object,
  onFilterChange: PropTypes.func,
}

export default Filter