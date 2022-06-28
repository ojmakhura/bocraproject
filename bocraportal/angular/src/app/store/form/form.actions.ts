// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { FormFieldVO } from '@app/model/bw/org/bocra/portal/form/field/form-field-vo';
import { FormVO } from '@app/model/bw/org/bocra/portal/form/form-vo';
import { FormCriteria } from '@app/model/bw/org/bocra/portal/form/form-criteria';
import { FormSectionVO } from '@app/model/bw/org/bocra/portal/form/section/form-section-vo';

export enum FormActionType {
    FIND_FORM_BY_ID = '[Form] Find Form By Id',
    FIND_FORM_BY_ID_SUCCESS = '[Form] Form Find By Id Success',
    SAVE_FORM = '[Form] Save Form',
    SAVE_FORM_SUCCESS = '[Form] Save Form Success',
    REMOVE_FORM = '[Form] Remove Form',
    REMOVE_FORM_SUCCESS = '[Form] Remove Form Success',
    GET_ALL_FORMS = '[Form] Get All Forms',
    GET_ALL_FORMS_SUCCESS = '[Form] Get All Forms Success',
    SEARCH_FORMS = '[Form] Search Forms',
    SEARCH_FORMS_SUCCESS = '[Form] Search Forms Success',
    GET_ALL_FORMS_PAGED = '[Form] Get All Forms Paged',
    GET_ALL_FORMS_PAGED_SUCCESS = '[Form] Get All Forms Paged Success',
    FIND_FIELD_BY_ID = '[Form] Find Field By Id',
    FIND_FIELD_BY_ID_SUCCESS = '[Form] Find Field By Id Success',
    SAVE_FIELD = '[Form] Save Field',
    SAVE_FIELD_SUCCESS = '[Form] Save Field Success',
    SAVE_SECTION = '[Form] Save Section',
    SAVE_SECTION_SUCCESS = '[Form] Save Section Success',
    SET_SECTIONS = '[Form] Set Sections',
    REMOVE_SECTION = '[Form] Remove Section',
    REMOVE_SECTION_SUCCESS = '[Form] Remove Section Success',
    REMOVE_FIELD = '[Form] Remove Field',
    REMOVE_FIELD_SUCCESS = '[Form] Remove Field Success',
    GET_ALL_FIELDS = '[Form] Get All Fields',
    GET_ALL_FIELDS_SUCCESS = '[Form] Get All Fields Success',
    GET_ALL_FIELDS_PAGED = '[Form] Get All Fields Paged',
    GET_ALL_FIELDS_PAGED_SUCCESS = '[Form] Get All Fields Paged Success',
    FORM_LOADING = '[Form] Form Loading',
    FORM_SUCCESS = '[Form] Form Success',
    FORM_RESET = '[Form] Form Reset',
    FORM_FAILURE = '[Form] Form Action Failure'
}

export const findFormById = createAction(
    FormActionType.FIND_FORM_BY_ID,
    props<{ id: number | any, loading: boolean }>()
);

export const findFormByIdSuccess = createAction(
    FormActionType.FIND_FORM_BY_ID_SUCCESS,
    props<{ form: FormVO | any, messages: any[], success: boolean }>()
);

export const saveForm = createAction(
    FormActionType.SAVE_FORM,
    props<{ form: FormVO | any, loading: boolean }>()
);

export const saveFormSuccess = createAction(
    FormActionType.SAVE_FORM_SUCCESS,
    props<{ form: FormVO | any, messages: any[], success: boolean }>()
);

export const removeForm = createAction(
    FormActionType.REMOVE_FORM,
    props<{ id: number | any, loading: boolean  }>()
);

export const removeFormSuccess = createAction(
    FormActionType.REMOVE_FORM_SUCCESS,
    props<{ removed: boolean | any, messages: any[], success: boolean }>()
);

export const getAllForms = createAction(
    FormActionType.GET_ALL_FORMS,
    props<{ loading: boolean }>()
);

export const getAllFormsSuccess = createAction(
    FormActionType.GET_ALL_FORMS_SUCCESS,
    props<{ forms: FormVO[] | any[], messages: any[], success: boolean }>()
);

export const searchForms = createAction(
    FormActionType.SEARCH_FORMS,
    props<{ criteria: FormCriteria | any , loading: boolean }>()
);

export const searchFormsSuccess = createAction(
    FormActionType.SEARCH_FORMS_SUCCESS,
    props<{ forms: FormVO[] | any[], messages: any[], success: boolean }>()
);

export const getAllFormsPaged = createAction(
    FormActionType.GET_ALL_FORMS_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any, loading: boolean }>()
);

export const getAllFormsPagedSuccess = createAction(
    FormActionType.GET_ALL_FORMS_PAGED_SUCCESS,
    props<{ forms: FormVO[] | any[], messages: any[], success: boolean }>()
);

export const findFieldById = createAction(
    FormActionType.FIND_FIELD_BY_ID,
    props<{ id: number | any, loading: boolean }>()
);

export const findFieldByIdSuccess = createAction(
    FormActionType.FIND_FIELD_BY_ID_SUCCESS,
    props<{ formField: FormFieldVO | any, messages: any[], success: boolean }>()
);

export const saveField = createAction(
    FormActionType.SAVE_FIELD,
    props<{ formField: FormFieldVO | any, loading: boolean }>()
);

export const saveFieldSuccess = createAction(
    FormActionType.SAVE_FIELD_SUCCESS,
    props<{ formField: FormFieldVO | any, messages: any[], success: boolean }>()
);

export const saveSection = createAction(
    FormActionType.SAVE_SECTION,
    props<{ formSection: FormSectionVO | any, loading: boolean }>()
);

export const saveSectionSuccess = createAction(
    FormActionType.SAVE_SECTION_SUCCESS,
    props<{ formSection: FormSectionVO | any, messages: any[], success: boolean }>()
);

export const setSections = createAction(
    FormActionType.SET_SECTIONS,
    props<{ formSections: FormSectionVO[] }>()
);

export const removeSection = createAction(
    FormActionType.REMOVE_SECTION,
    props<{ id: number | any, loading: boolean }>()
);

export const removeSectionSuccess = createAction(
    FormActionType.REMOVE_SECTION_SUCCESS,
    props<{ removed: boolean | any, messages: any[], success: boolean }>()
);

export const removeField = createAction(
    FormActionType.REMOVE_FIELD,
    props<{ id: number | any, loading: boolean }>()
);

export const removeFieldSuccess = createAction(
    FormActionType.REMOVE_FIELD_SUCCESS,
    props<{ removed: boolean | any, messages: any[], success: boolean }>()
);

export const getAllFields = createAction(
    FormActionType.GET_ALL_FIELDS,
    props<{ loading: boolean }>()
);

export const getAllFieldsSuccess = createAction(
    FormActionType.GET_ALL_FIELDS_SUCCESS,
    props<{ formFields: FormFieldVO[] | any, messages: any[], success: boolean }>()
);

export const getAllFieldsPaged = createAction(
    FormActionType.GET_ALL_FIELDS_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any, loading: boolean }>()
);

export const getAllFieldsPagedSuccess = createAction(
    FormActionType.GET_ALL_FIELDS_PAGED_SUCCESS,
    props<{ formFields: FormFieldVO[] | any, messages: any[], success: boolean }>()
);

export const formReset = createAction(FormActionType.FORM_RESET);

export const formLoading = createAction(
    FormActionType.FORM_LOADING,
    props<{ messages: any[], success: boolean, loading: boolean }>()
);

export const formSuccess = createAction(
    FormActionType.FORM_SUCCESS,
    props<{ messages: any[], success: boolean, loading: boolean }>()
);

export const formFailure = createAction(
    FormActionType.FORM_FAILURE,
    props<{ messages: any[] }>()
);
