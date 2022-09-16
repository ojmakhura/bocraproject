// Generated by andromda-angular cartridge (app\usecase\state.store.ts.vsl) DO NOT EDIT
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { FormCriteria } from '@app/model/bw/org/bocra/portal/form/form-criteria';
import { FieldType } from '@app/model/bw/org/bocra/portal/form/field/field-type';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import { FormSectionVO } from '@app/model/bw/org/bocra/portal/form/section/form-section-vo';
import { LicenseeFormVO } from '@app/model/bw/org/bocra/portal/licensee/form/licensee-form-vo';

export const formKey = 'form';

export interface FormState {
  criteria: FormCriteria | any;
  fieldType: FieldType | any;
  formFields: Array<FormFieldVO> | Array<any>;
  formField: FormFieldVO | any;
  formSection: FormSectionVO | any;
  formSections: Array<FormSectionVO> | Array<any>;
  licenseeForms: Array<LicenseeFormVO> | Array<any>;
  licenseeForm: LicenseeFormVO | any;
  form: FormVO | any;
  required: Boolean | any;
  min: String | any;
  id: number | any;
  fieldName: String | any;
  forms: Array<FormVO> | Array<any>;
  max: String | any;
  defaultValue: String | any;
  removed: boolean;
  loaderMessage: string | undefined,
  success: boolean;
  loading: boolean;
  error: boolean,
  messages: any[];
}

export const initialState: FormState = {
  criteria: null,
  fieldType: null,
  formFields: [],
  formField: null,
  formSection: null,
  formSections: [],
  licenseeForms: [],
  licenseeForm: null,
  form: null,
  required: null,
  min: null,
  id: null,
  fieldName: null,
  forms: [],
  max: null,
  defaultValue: null,
  removed: false,
  success: false,
  loading: false,
  error: false,
  messages: [],
  loaderMessage: undefined
};
