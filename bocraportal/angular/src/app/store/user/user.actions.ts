// Generated by andromda-angular cartridge (app\usecase\action.store.ts.vsl) DO NOT EDIT
import { createAction, props } from '@ngrx/store';
import { UserVO } from '@app/model/bw/org/bocra/portal/user/user-vo';
import { LicenseeVO } from '@app/model/bw/org/bocra/portal/licensee/licensee-vo';
import { LicenseeCriteria } from '@app/model/bw/org/bocra/portal/licensee/licensee-criteria';

export enum UserActionType {
    CREATE_USER = '[User] Create User',
    CREATE_USER_SUCCESS = '[User] Create User Success',
    UPDATE_USER_NAME = '[User] Update User Name',
    UPDATE_USER_NAME_SUCCESS = '[User] Update User Name Success',
    LOAD_USERS = '[User] Load Users',
    LOAD_USERS_SUCCESS = '[User] Load Users Success',
    FIND_BY_ID = '[User] Find By Id',
    FIND_BY_ID_SUCCESS = '[User] Find By Id Success',
    SAVE = '[User] Save',
    SAVE_SUCCESS = '[User] Save Success',
    REMOVE = '[User] Remove',
    REMOVE_SUCCESS = '[User] Remove Success',
    GET_ALL = '[User] Get All',
    GET_ALL_SUCCESS = '[User] Get All Success',
    SEARCH = '[User] Search',
    SEARCH_SUCCESS = '[User] Search Success',
    GET_ALL_PAGED = '[User] Get All Paged',
    GET_ALL_PAGED_SUCCESS = '[User] Get All Paged Success',
    USER_RESET = '[User] User Reset',
    USER_FAILURE = '[User] User Action Failure',
    USER_LOADING = '[User] User Loading'
}

export const createUser = createAction(
    UserActionType.CREATE_USER,
    props<{ user: UserVO | any , loading: boolean }>()
);

export const createUserSuccess = createAction(
    UserActionType.CREATE_USER_SUCCESS,
    props<{ user: UserVO | any, success: boolean}>()
);

export const updateUserName = createAction(
    UserActionType.UPDATE_USER_NAME,
    props<{ username: string | any , userId: string | any , loading: boolean }>()
);

export const updateUserNameSuccess = createAction(
    UserActionType.UPDATE_USER_NAME_SUCCESS,
    props<{ updated: Boolean | any, success: boolean}>()
);

export const loadUsers = createAction(
    UserActionType.LOAD_USERS,
    props<{  loading: boolean }>()
);

export const loadUsersSuccess = createAction(
    UserActionType.LOAD_USERS_SUCCESS,
    props<{ users: UserVO[] | any, success: boolean}>()
);

export const findById = createAction(
    UserActionType.FIND_BY_ID,
    props<{ id: number | any , loading: boolean }>()
);

// export const findByIdSuccess = createAction(
//     UserActionType.FIND_BY_ID_SUCCESS,
//     props<{ licenseeVO | any: LicenseeVO | any, success: boolean}>()
// );

// export const save = createAction(
//     UserActionType.SAVE,
//     props<{ licensee: LicenseeVO | any , loading: boolean }>()
// );

// export const saveSuccess = createAction(
//     UserActionType.SAVE_SUCCESS,
//     props<{ licenseeVO | any: LicenseeVO | any, success: boolean}>()
// );

export const remove = createAction(
    UserActionType.REMOVE,
    props<{ id: number | any , loading: boolean }>()
);

// export const removeSuccess = createAction(
//     UserActionType.REMOVE_SUCCESS,
//     props<{ boolean | any: boolean | any, success: boolean}>()
// );

export const getAll = createAction(
    UserActionType.GET_ALL,
    props<{  loading: boolean }>()
);

// export const getAllSuccess = createAction(
//     UserActionType.GET_ALL_SUCCESS,
//     props<{ licenseeVO[] | any: LicenseeVO[] | any, success: boolean}>()
// );

export const search = createAction(
    UserActionType.SEARCH,
    props<{ criteria: LicenseeCriteria | any , loading: boolean }>()
);

// export const searchSuccess = createAction(
//     UserActionType.SEARCH_SUCCESS,
//     props<{ licenseeVO[] | any: LicenseeVO[] | any, success: boolean}>()
// );

export const getAllPaged = createAction(
    UserActionType.GET_ALL_PAGED,
    props<{ pageNumber: number | any , pageSize: number | any , loading: boolean }>()
);

// export const getAllPagedSuccess = createAction(
//     UserActionType.GET_ALL_PAGED_SUCCESS,
//     props<{ licenseeVO[] | any: LicenseeVO[] | any, success: boolean}>()
// );


export const userReset = createAction(UserActionType.USER_RESET);

export const userLoading = createAction(
    UserActionType.USER_LOADING,
    props<{ loading: boolean, success: boolean, error: any }>()
);

export const userFailure = createAction(
    UserActionType.USER_FAILURE,
    props<{ error: any, success: boolean, loading: boolean }>()
);
