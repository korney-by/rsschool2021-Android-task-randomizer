package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var min_value: EditText? = null
    private var max_value: EditText? = null
    private lateinit var showFragments: ShowFragments

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        min_value = view.findViewById(R.id.min_value)
        max_value = view.findViewById(R.id.max_value)


        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            val min = Utils.getInt(min_value)
            val max = Utils.getInt(max_value)

            if (isValuesGood(min, max)) {
                showFragments?.showSecondFragment(min, max)
            } else {
                Toast.makeText(context, getMessageBadValues(min, max), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ShowFragments) {
            showFragments = context as ShowFragments
        } else {
            throw  RuntimeException(context.toString() + " must implement ShowFragments");
        }
    }

    fun isValuesGood(min: Int, max: Int): Boolean {
        return (min < max) && (min_value?.text.toString().length > 0);
    }

    fun getMessageBadValues(min: Int, max: Int): String {
        val min_length = min_value?.text.toString().length
        val max_length = max_value?.text.toString().length

        if (min_length == 0 && max_length == 0) {
            return getString(R.string.messageInputMinMax)
        }

        if (min_length == 0) {
            return getString(R.string.messageInputMin)
        }

        if (max_length == 0) {
            return getString(R.string.messageInputMax)
        }

        if (min >= max) {
            return getString(R.string.messageMaxMoreMin)
        }
        return ""
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}


